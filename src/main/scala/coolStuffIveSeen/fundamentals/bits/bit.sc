/**
 * Bitwise right Shift (>>): Takes two numbers, right shifts the bits of the first operand,
 * the second operand decides the number of places to shift
 *
 *
 * Bitwise left Shift (<<): Takes two numbers, left shifts the bits of the first operand,
 * the second operand decides the number of places to shift.
 *
 *
 * Bitwise shift right zero fill(>>>): In shift right zero fill operator,
 * left operand is shifted right by the number of bits specified by the right operand,
 * and the shifted values are filled up with zeros.
 *
 */

val itvIdentifier1: Short = 16383

Integer.toBinaryString(itvIdentifier1)

// to extract BBBBBB we need to shift 6 bits right so

val BBBBBBBB1 = itvIdentifier1 >>> 6 // Int = 255
Integer.toBinaryString(BBBBBBBB1)

val SSSSSS1 = itvIdentifier1 - (BBBBBBBB1 << 6) // Int = 63
Integer.toBinaryString(SSSSSS1)

println("example 2")


val itvIdentifier2: Short = 16382

Integer.toBinaryString(itvIdentifier1)

val BBBBBBBB2 = itvIdentifier2 >>> 6 // Int = 255
Integer.toBinaryString(BBBBBBBB2)

val SSSSSS2 = itvIdentifier2 - (BBBBBBBB2 << 6) // Int = 62
Integer.toBinaryString(SSSSSS2)
Integer.toBinaryString(SSSSSS2).length


println("example 3")


val itvIdentifier3: Short = 16384

Integer.toBinaryString(itvIdentifier3)

val BBBBBBBB3 = itvIdentifier3 >>> 6 // Int = 256
Integer.toBinaryString(BBBBBBBB3)
Integer.toBinaryString(BBBBBBBB3).length

val SSSSSS3 = itvIdentifier3 - (BBBBBBBB3 << 6) // Int = 0
Integer.toBinaryString(SSSSSS3)

/** ------------------------------ */

val itvIdentifier: Short = 5469 // 00 01010101011101
itvIdentifier.toBinaryString // String = 1010101011101
itvIdentifier.toBinaryString.length

// 00 01010101 011101
// 00 BBBBBBBB SSSSSS

/**
 * 01010101 -> (1x1) + (0x2) + (1x4) + (0x8) + (1x16) + (0x32) + (1x64) = 85 ==> BBBBBBBB = 85
 * 011101   -> (1x1) + (0x2) + (1x4) + (1x8) + (1x16) = 29 ==> SSSSSS = 29
 */

// so to get BBBBBB, we need to shift 6 places to the right, removing SSSSSS
// AND fill the previous positions with zeros

val BBBBBBBB = itvIdentifier >>> 6 // Int = 85
Integer.toBinaryString(BBBBBBBB) // 1010101 == 01010101

// to get SSSSSS we need to shift back to where it was i.e left <<
val SSSSSS = itvIdentifier - (BBBBBBBB << 6) // Int = 29
Integer.toBinaryString(SSSSSS) // 11101 == 011101





