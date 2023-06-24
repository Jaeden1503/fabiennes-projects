import cv2
import pytesseract

pytesseract.pytesseract.tesseract_cmd = r'C:\Program Files\Tesseract-OCR\tesseract.exe'

camera = cv2.VideoCapture(0)

while True:
    _,Image = camera.read()
    cv2.imshow("Text detection", Image)
    if cv2.waitKey(1) & 0xFF==ord('s'): #when s key is pressed webcam will take a picture and close
        cv2.imwrite('test1.jpg', Image)
        break
camera.release()
cv2.destroyAllWindows()