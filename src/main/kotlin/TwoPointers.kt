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

    fun removeDuplicates(nums: IntArray): Int {
        var rI = 0
        var wI = 1
        while (rI < nums.size - 1) {
            if (nums[rI] == nums[rI + 1]) {
                rI++
            } else {
                nums[wI] = nums[rI + 1]
                wI++
            }
        }
        return wI + 1
    }

    fun threeSum(nums: IntArray): List<List<Int>> {
        nums.sort()
        print(nums.contentToString())
        val retList = mutableListOf<List<Int>>()
        for (i in nums.indices) {
            if (i > 0 && nums[i] == nums[i - 1]) continue
            var j = i + 1
            if (nums[i] == nums[j]) continue
            var k = nums.size - 1
            while (j < k) {
                val sum = nums[i] + nums[j] + nums[k]
                if (sum == 0) {
                    retList.add(listOf(nums[i], nums[j], nums[k]))
                    j++
                    while (j < k && nums[j] == nums[j + 1]) {
                        j++
                    }
                } else if (sum > 0) {
                    k--
                } else {
                    j++
                }
            }
        }
        return retList
    }

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        var size = 0
        var temp = head
        while (temp != null) {
            size++
            temp = temp.next
        }
        temp = head
        val nodeToRem = size - n
        if (nodeToRem == 0) {
            return head?.next
        }
        var i = 0
        while (temp != null) {
            if (i + 1 == nodeToRem) {
                temp.next = temp.next?.next
                return head
            } else {
                i++
                temp = temp.next
            }
        }
        return head
    }
}