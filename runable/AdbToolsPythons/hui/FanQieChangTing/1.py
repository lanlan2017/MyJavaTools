# 2024-06-15 20:48:44 Java 自动生成
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
scrcpyOrder = 3
# 要查找的区域
region = (scrcpyOrder * part_width, 140, part_width, 738)

images = [
    sys.path[0]+"\\begin_+10_A_KanShiPInLiJiXuQian_0.png",
    sys.path[0]+"\\begin_+10_A_KanShiPinQianDaoDe_0.png",
    sys.path[0]+"\\begin_+10_EWaiLing_0.png",
    sys.path[0]+"\\begin_+10_EWaiLing_1.png",
    sys.path[0]+"\\begin_+10_JuXuGuanKan_0.png",
    sys.path[0]+"\\begin_+10_JuXuGuanKan_2.png",
    sys.path[0]+"\\begin_+10_KanShiPinLiJiLingQu_0.png",
    sys.path[0]+"\\begin_+10_KanShiPinLing_0.png",
    sys.path[0]+"\\begin_+10_KanShiPinLing_1.png",
    sys.path[0]+"\\begin_+10_KanShiPinLing_2.png",
    sys.path[0]+"\\begin_+10_KanShiPinZaiDe_0.png",
    sys.path[0]+"\\begin_+10_KanShiPinZaiLing_0.png",
    sys.path[0]+"\\begin_+10_KanShiPinZaiLing_1.png",
    sys.path[0]+"\\begin_+10_KanShiPinZaiLing_2.png",
    sys.path[0]+"\\begin_+10_KanShiPinZuiGaoKeLing_0.png",
    sys.path[0]+"\\begin_+10_LingQuJiangLi.png",
    sys.path[0]+"\\begin_+10_ZaiKanYiGeHuoQuZhuiGao_0.png",
    sys.path[0]+"\\begin_+10_ZaiKanYiGeHuoQuZhuiGao_1.png",
    sys.path[0]+"\\begin_+10_ZaiKanYiGeHuoQuZhuiGao_2.png",
    sys.path[0]+"\\begin_+10_ZaiKanYiGejiXuLingQu_0.png",
    sys.path[0]+"\\begin_+10_ZaiKanYiGeJiXuLingQu_1.png",
    sys.path[0]+"\\begin_+10_ZaiKanYiGeJiXuLingQu_2.png",
    sys.path[0]+"\\begin_A_+10_EWaiLing.png",
    sys.path[0]+"\\click_A_LingWanCanBuTie_).png",
    sys.path[0]+"\\click_A_QuGuangJieJiXuLingQu_TuiChu_0.png",
    sys.path[0]+"\\click_A_TingShuZhuanJinBi_LiJiLingQu_0.png",
    sys.path[0]+"\\click_A_TingShuZhuanJinBi_LiJiLingQu_1.png",
    sys.path[0]+"\\click_A_TingShuZhuanJinBi_LiJiLingQu_2.png",
    sys.path[0]+"\\click_A_TingShuZhuanJinBi_LiJiLing_0.png",
    sys.path[0]+"\\click_Z_GuangGao_GuanBi.png",
    sys.path[0]+"\\click_Z_KaiBaoXiang_0.png",
    sys.path[0]+"\\return_ZhiBo_GouWuChe_0.png",
    sys.path[0]+"\\return_ZhiBo_JinRuZhiBo_0.png"
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

