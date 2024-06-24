import sys

img1 = sys.path[0]+"\YueDu_JieSuoZhangJie.png"
img2 = sys.path[0]+"\YueDu_KanWanShiPinZaiLing.png"
img3 = sys.path[0]+"\YueDu_LiJiLingQu.png"

img4 = sys.path[0]+"\YueDu_TianJiangHongBao.png"
img5 = sys.path[0]+"\YueDu_TianJiangHongBao1.png"
imgs = [img1, img2, img3, img4, img5]

for x in imgs:
    lastIndex = str(x).rfind("_")
    print(x[lastIndex+1:])
