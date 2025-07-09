class BasicArrayOperations {

    fun containsDuplicate(nums: IntArray): Boolean {
        nums.sort()
        for (i in 0..nums.size - 2) {
            if (nums[i] == nums[i + 1]) {
                return true
            }
        }
        return false
    }

    fun isAnagram(s: String, t: String): Boolean {
        val freqArr = IntArray(26)
        for (c in s) {
            freqArr[c - 'a'] += 1
        }
        for (c in t) {
            freqArr[c - 'a'] -= 1
        }
        return !freqArr.any { it != 0 }
    }

    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = mutableMapOf<Int, Int>()
        for (i in nums.indices) {
            val num = nums[i]
            if (map.contains(target - num)) {
                return intArrayOf(i, map[target - num]!!)
            }
            map[num] = i
        }
        return intArrayOf()
    }

    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val freqArr = IntArray(26)
        val map = mutableMapOf<String, MutableList<String>>()
        for (str in strs) {
            val key = freqArrAsKey(str, freqArr)
            map.getOrPut(key) {
                mutableListOf()
            }.add(str)
        }
        return map.values.toList()
    }

    fun freqArrAsKey(str: String, freqArr: IntArray): String {
        for (s in str) {
            freqArr[s - 'a'] += 1
        }
        val key = StringBuilder()
        for (f in freqArr) {
            key.append("$f#")
        }
        for (i in freqArr.indices) {
            freqArr[i] = 0
        }
        return key.toString()
    }
}