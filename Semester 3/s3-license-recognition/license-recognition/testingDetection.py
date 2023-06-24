import cv2
import pytesseract
import operator as op

pytesseract.pytesseract.tesseract_cmd = r'C:\Program Files\Tesseract-OCR\tesseract.exe'
#wokring images with (1200, 800) 6, 12, 14
#wokring images with (600, 400) 6, 12, 1, 15

# img = cv2.imread("images/car_14.jpg")
# img = cv2.resize(img, (1200,800)) #(600,400)
# cv2.imshow("car", img)
# cv2.waitKey(0)
# cv2.destroyAllWindows()

camera = cv2.VideoCapture(0)

while True:
    _,image = camera.read()
    cv2.imshow("Text detection", image)
    if cv2.waitKey(1) & 0xFF==ord('s'): #when s key is pressed webcam will take a picture and close
        cv2.imwrite('test1.jpg', image)
        break
camera.release()
cv2.destroyAllWindows()
img = cv2.imread("test1.jpg")


gray = cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)
cv2.imshow("car_gray", gray)
cv2.waitKey(0)
cv2.destroyAllWindows()


filter = cv2.bilateralFilter(gray, 13,15,15) #remove unwanted noise, like black dots
edge = cv2.Canny(filter, 170,200) # finds edges in an image
cv2.imshow("car_edges", edge )
cv2.waitKey(0)
cv2.destroyAllWindows()


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


# create a string of allowed characters
allowed_chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
 
s = ""
for i in text:
    if op.countOf(allowed_chars, i) > 0:
        s += i
# printing final string
print("final string", s)