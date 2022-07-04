import pyautogui
import time
import sys

img = sys.path[0]+"\yuedu_jiesuozhangjie.png"
# print(img)

# img = "waterRPA_douyin\my\my_return.png"
# 循环查找图片
while True:
    location = pyautogui.locateCenterOnScreen(img, confidence=0.9)
    if location is not None:
        # print("找到+"+img)
        print(location)
        break
    else:
        # print("没找到:"+img)
        # 等待
        time.sleep(0.5)
