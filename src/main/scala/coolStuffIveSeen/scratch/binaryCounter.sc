val binary = 36
val binaryString = binary.toBinaryString

// (0x1) + (0x2) + (1x4) + (0x8) + (0x16) + (1x32)

// 100101
// (1x1) + (0x2) + (1x4) + (0x8) + (0x16) + (1x32) == 37


def countBinary(binaryString: String, count: Int = 0, multiple: Int = 1): Int = {
  println(s"count: $count")
  println(s"multiple: $multiple")
  println(s"Incoming binary string:${binaryString}")
  if (binaryString.isEmpty) {
    count
  }
  else {
    binaryString.charAt(binaryString.length - 1).toString match {
      case "1" =>
        if (multiple == 1) {
          println("multiple is 1 so only add 1")
          countBinary(binaryString.splitAt(binaryString.length - 1)._1, count + (1 * multiple), multiple + 1)
        } else {
          println("multiple is 1 so add 2 to multiple")
          countBinary(binaryString.splitAt(binaryString.length - 1)._1, count + (1 * multiple), multiple * 2)
        }

      case _ =>
        countBinary(binaryString.splitAt(binaryString.length - 1)._1, count, multiple * 2)
    }
  }
}

countBinary(binaryString)
countBinary("100101")

"0".concat("check")

val x = 11948
x.toBinaryString
countBinary("101100")

11948 >>> 6
(6 << 6)

// ParsedId(13668,0,6834)