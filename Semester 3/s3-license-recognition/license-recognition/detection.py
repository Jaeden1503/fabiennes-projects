import cv2
import pytesseract
import requests
import json
import operator as op

pytesseract.pytesseract.tesseract_cmd = r'C:\Program Files\Tesseract-OCR\tesseract.exe'

#Insert code for camera here
camera = cv2.VideoCapture(0)

while True:
    _,image = camera.read()
    cv2.imshow("Text detection", image)
    if cv2.waitKey(1) & 0xFF==ord('s'): #when s key is pressed webcam will take a picture and close
        cv2.imwrite('detectedLicense.jpg', image)
        break
camera.release()
cv2.destroyAllWindows()
img = cv2.imread("detectedLicense.jpg")

#Code for using an image
# img = cv2.imread("images/car_14.jpg")
# img = cv2.resize(img, (1200,800)) #(600,400)
# img = cv2.imread("workingCamera1.jpg")


gray = cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)

filter = cv2.bilateralFilter(gray, 13,15,15) #remove unwanted noise, like black dots
edge = cv2.Canny(filter, 170,200) # finds edges in an image

contor, href=cv2.findContours(edge.copy(), cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE) # finds contours in a binary image

ctn = sorted(contor, key=cv2.contourArea, reverse=True)[:10]

for c in ctn:
    peri=cv2.arcLength(c, True)
    epsilon=0.018*peri
    apporx=cv2.approxPolyDP(c,epsilon,True)
    if len(apporx)==4:
        x,y,w,h = cv2.boundingRect(apporx)
        cimg = img[y:y+h, x:x+w] #cropped image
        cv2.imshow("plate", cimg) 
        text=pytesseract.image_to_string(cimg, lang="eng")
        print(text)
        final = cv2.drawContours(img,[apporx],-1,(255,0,0),3)
        break

cv2.imshow("plate detected", img)
cv2.waitKey(0)
cv2.destroyAllWindows()

#remove accidental character from license plate and create a string of allowed characters
allowed_chars = "0123456789BCDFGHJKLMNPQRSTVWXYZ"
 
properLicense = ""
for i in text:
    if (i == "O"):
        i = "0"
    if op.countOf(allowed_chars, i) > 0:   
        properLicense += i

#send detected license plate to back end
# cameraData= {
#     "licensePlate":"ABC321"
# }

cameraData= {
    "licensePlate": properLicense
}

headersSet={"Content-type":"application/json"}
inputData = json.dumps(cameraData)

req = requests.post("http://localhost:8080/license/detection", data=inputData, headers=headersSet)
print(req) #repsonse code eg. 200
print(req.text) #response from backend