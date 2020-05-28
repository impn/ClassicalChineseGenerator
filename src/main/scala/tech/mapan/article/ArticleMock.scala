package tech.mapan.article

import scala.io.Source
import scala.util.Random

/**
 * 随机生成一篇文言文
 */

object ArticleMock {

  def article(words: Int, para: Int): String = {

    // 以流的方式获取1.txt
    val stream = TitleMock.getClass.getResourceAsStream("/1.txt")

    // 剥离出词语组成一个List
    val list: List[String] = Source.fromInputStream(stream)("UTF8").getLines.toList.map {
      f => {
        f.split("\t").toList.head
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
