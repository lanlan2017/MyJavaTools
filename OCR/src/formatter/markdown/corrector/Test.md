try{
    //使用 Scanner来读取网络输入流中的数据
    Scanner scan = new Scanner(s.getInputstream());
    //读取一行字符
    string line = scan.nextline();
}
//捕获 SocketTimeoutException异常
catch(SocketTimeoutException ex)
{
    //对异常进行处理
}