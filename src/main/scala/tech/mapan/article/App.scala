package tech.mapan.article

import java.io.{File, FileOutputStream, OutputStreamWriter}
import java.time.{LocalDate, LocalTime}

import scala.util.Random

/**
  * 传入两个参数，第一个参数是一段文字的字数
  * 第二个参数是生成几篇文章。
  */
object App {
  def main(args: Array[String]): Unit = {

    // 判断有没有输入参数
    var i = 1
    if (args.length != 0) {
      i = args(0).toInt
    }

    // 每段文章多少词？生成几段文章？
    for (j <- 1 to i) {
      // 随机生成2-5段
      val para = math.abs(Random.nextInt) % 4 + 2
      // 生成120词的文章
      val totalArticle = ArticleMock.article(120, para)
      val title = TitleMock.title

      // 随机出来一个近20年的时间点
      val ran = Random.nextInt()
      val year = 1980 + math.abs(ran % 39)
      val month = 1 + math.abs(ran % 12)
      val day = 1 + math.abs(ran % 28)

      val now = LocalDate.of(year, month, day) + " " + LocalTime.now().toString.dropRight(4)


      // 这是markdown文章的前缀
      val pre = "---\n" + "title: " + title + "\ndate: " + now + "\ntags: \n" + "---\n\n"

      // 输出文章路径output文件夹，如果没有就创建
      val myPath: File = new File("./output")
      if (!myPath.exists()){//若此目录不存在，则创建之
        myPath.mkdir()
      }

      val fos : FileOutputStream = new FileOutputStream(s"./$myPath/$title.md")
      val osw : OutputStreamWriter  = new OutputStreamWriter(fos, "UTF-8")

      // 写入文章
      osw.write(pre + totalArticle)
      osw.close()
      fos.close()

      println(s"----------生成第 $j 篇文章----------")
      println(pre)
    }
  }
}
