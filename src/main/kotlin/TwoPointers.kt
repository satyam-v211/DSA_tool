class TwoPointers {
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        for (i in numbers.indices) {
            val num = numbers[i]
            val complementIndex = binarySearch(numbers, i + 1, numbers.size - 1, target)
            if (complementIndex != -1) {
                return intArrayOf(num, numbers[complementIndex])
            }
        }
        return intArrayOf()
    }

    fun twoSum2(numbers: IntArray, target: Int): IntArray {
        var left = 0
        var right = numbers.size - 1
        while (left < right) {
            val sum = numbers[left] + numbers[right]
            if (sum > target) {
                right--
            } else if (sum < target) {
                left++
            } else {
                return intArrayOf(left + 1, right + 1)
            }
        }
        return intArrayOf()
    }

    fun binarySearch(numbers: IntArray, start: Int, end: Int, target: Int): Int {
        if (start > end) {
            return -1
        }
        val mid = start + (end - start) / 2
        return if (numbers[mid] == target) {
            return mid
        } else if (numbers[mid] > target) {
            binarySearch(numbers, start, mid - 1, target)
        } else {
            binarySearch(numbers, mid + 1, end, target)
        }
    }
}