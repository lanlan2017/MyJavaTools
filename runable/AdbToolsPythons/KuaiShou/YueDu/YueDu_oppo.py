# 2022-06-18 23:51:52 Java 自动生成
import pyautogui
import time
import sys
import os

# 获取当前文件的文件名（简单文件名，不包含绝对路径）
name = str(os.path.basename(__file__))
# 从右向左查找字符'.'
index = name.rfind(".")
# 拼接出存放当前python进程pid的.txt文件的绝对路径
pidFile = sys.path[0]+"\\"+name[0:index]+".txt"
# 打开文件
f = open(pidFile, "w")
# 把当前文件的pid写入文件中
f.write(str(os.getpid()))
f.close()

images = [
    sys.path[0]+"\\begin_oppo_JieSuoZhangJie.png",
    sys.path[0]+"\\begin_oppo_KanGuangGaoMiao20FenZhong1.png",
    sys.path[0]+"\\begin_oppo_KanGuangGaoMiao20FenZhong2.png",
    sys.path[0]+"\\begin_oppo_KanWanShiPinZaiLing.png",
    sys.path[0]+"\\begin_oppo_LiJiLingQu.png",
    sys.path[0]+"\\begin_oppo_LiJiLingQu0.png",
    sys.path[0]+"\\begin_oppo_LiJiLingQu1.png",
    sys.path[0]+"\\begin_oppo_MianGuangGao.png"
]

# 循环查找图片
flag = True
while flag:
    for x in images:
        # 在屏幕上查找指定的图片
        location = pyautogui.locateCenterOnScreen(x, confidence=0.9)
        # 如果找到指定的图片
        if location is not None:
            print(x[x.rfind("\\")+1:], location)
            # 修改外层循环标志，退出外层循环
            flag = False
            # 退出内层循环
            break
        else:
            # 等待
            time.sleep(0.05)

