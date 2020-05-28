package tech.mapan.article
import scala.io.Source
import scala.util.Random

/**
 * 随机生成一篇文言文标题
 */
object TitleMock {

  def title: String = {

    // 获取1.txt
    val i1 = TitleMock.getClass.getResourceAsStream("/1.txt")

    // 导入常用汉语库
    val source =  Source.fromInputStream(i1)("UTF8")

    // 剥离出每一行数据
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
