function bubbleSort(array) {
    var temp = 0;
    for (var i = 0; i < array.length - 1; i++) {
        for (var j = 0; j < array.length - 1 - i; j++) {
            // 如果后面的比前面的大
            if (array[j] < array[j + 1]) {
                // 将小的数缓存
                temp = array[j];
                // 将 大的 数放到前面
                array[j] = array[j + 1];
                // 将 小的 数放到后面
                array[j + 1] = temp;
            }
        }
    }
    return array;
}

var array = [3, 4, 1, 2];
console.log("排序前:" + array);
var sortarray = bubbleSort(array);
console.log("排序后:" + array);
