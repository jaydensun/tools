# [ClassVersion](https://github.com/jaydensun/tools/blob/master/src/main/java/ClassVersion.java)
提供两个方法：
## fileFolderVersion
搜索文件夹中的所有CLASS文件，打印类文件的版本号。
## libFolderVersion
搜索文件夹中的jar包，找出每个jar包的版本号（以第一个CLASS文件为准），然后打印所有jar包的类文件版本号。

# [SnappyTest](https://github.com/jaydensun/tools/blob/master/src/main/java/SnappyTest.java)
执行两个测试：

* write,write,flush
* write,flush,write,flush

两次都可以正确地还原出原始数据。但是，第一次的压缩结果要小一些。因此，使用时要尽量写入较多的数据，然后再进行压缩。
