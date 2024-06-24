# 2024-06-20 12:26:56 Java 自动生成
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

# 笔记本的显示器的宽度和高度
screen_width = 1366
screen_height = 768
# 要把屏幕分割成几份
parts = 5
# 计算每一份的宽度
part_width = screen_width // parts
# 确定是几个区域[0,4]
scrcpyOrder = 4
# 要查找的区域
region = (scrcpyOrder * part_width, 140, part_width, 738)

images = [
    sys.path[0]+"\\a_click_KaiXinShouXia.png",
    sys.path[0]+"\\a_click_LiJiLingQu.png",
    sys.path[0]+"\\a_click_LiJiLingQu_1.png",
    sys.path[0]+"\\a_return_KanZhiBoKaiBaoXiang.png",
    sys.path[0]+"\\begin_+10_A_KanShiPin.png",
    sys.path[0]+"\\begin_+10_A_KanShiPinEWaiZaiLing.png",
    sys.path[0]+"\\begin_+10_A_KanShiPinEWaiZaiLing_0.png",
    sys.path[0]+"\\begin_+10_A_KanShiPinZaiLing.png",
    sys.path[0]+"\\begin_+10_A_KanShiPin_1.png",
    sys.path[0]+"\\begin_+10_A_ZaiKanYiTiaoLing.png",
    sys.path[0]+"\\begin_+10_KanGuangGaoShiPin_QuZhuanQian.png",
    sys.path[0]+"\\begin_+10_KanShiPinLingQu.png",
    sys.path[0]+"\\begin_+10_KanShiPinZaiDe.png",
    sys.path[0]+"\\begin_+10_KanShiPinZaiDe_0.png",
    sys.path[0]+"\\begin_+10_KanShiPinZaiLing.png",
    sys.path[0]+"\\click_YuanQiu.png",
    sys.path[0]+"\\click_Z_KaiBaoXiang.png",
    sys.path[0]+"\\return_ZhiBo_BaoXiang.png"
]

# 循环查找图片
flag = True
while flag:
    for x in images:
        # 在屏幕上查找指定的图片
        location = pyautogui.locateCenterOnScreen(x, confidence=0.9,region=region)
        # 如果找到指定的图片
        if location is not None:
            print(x[x.rfind("\\")+1:], location)
            # 修改外层循环标志，退出外层循环
            flag = False
            # 退出内层循环
            break
        else:
            # 等待
            time.sleep(0.1)

