package tech.mapan.article

import scala.io.Source
import scala.util.Random

/**
 * 随机生成一篇文言文标题
 */
object TitleMock {

  def title: String = {

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

    //文言文虚词
    val array: Array[String] = Array("论", "行", "说", "颂", "学", "经", "序", "辞", "铭", "记", "赋")
    //虚词的数量
    val xuSize = array.length

    var title: String = ""
    while (title.length == 0 || title.length >= 3) {
      title = list(math.abs(Random.nextInt) % shiSize)
    }
    title + array(math.abs(Random.nextInt) % xuSize)
  }
}
