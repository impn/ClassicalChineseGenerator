package tech.mapan.article

import scala.io.Source
import scala.util.Random

/**
 * 随机生成一篇文言文
 */

object ArticleMock {

  def article(words: Int, para: Int): String = {

    // 获取1.txt
    val i1 = TitleMock.getClass.getResourceAsStream("/1.txt")

    // 导入常用汉语库
    val source =  Source.fromInputStream(i1)("UTF8")

    /**
     * 把词语转换成List
     */
    val lines: List[String] = source.getLines.toList
    val list: List[String] = lines.map {
      f => {
        val list = f.split("\t").toList
        var a1 = list.head // 词语
        var a2 = list(1) // 拼音
        var a3 = list(2) // 词频
        a1
      }
    }
    //实词的数量
    val shiSize = list.length
    val array: Array[String] = Array("而", "何", "乎", "乃", "其", "且", "然", "若", "所", "为", "焉", "也", "以", "矣", "于", "之", "则", "者", "与", "欤", "因", "兮")
    //虚词的数量
    val xuSize = array.length

    val prefix = ""
    // 前缀
    val suffix = "。" // 后缀
    var cont = ""

    for (j <- 1 to para) {
      var article: String = ""
      for (i <- 1 to 100) {

        article += list(math.abs(Random.nextInt) % shiSize) + array(math.abs(Random.nextInt) % xuSize)
        if (i != 100 && math.abs(Random.nextInt) % 2 == 0) {
          if (math.abs(Random.nextInt) % 3 == 0)
            article += "，"
          else
            article += "。"
        }
      }
      article = prefix + article + suffix + "\n\n"
      cont += article
    }
    cont
  }
}
