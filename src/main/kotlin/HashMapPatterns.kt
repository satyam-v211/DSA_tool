class HashMapPatterns {

    fun subarraySum(nums: IntArray, k: Int): Int {
        val prefixSumArr = IntArray(nums.size + 1)
        for (i in 1..prefixSumArr.size - 1) {
            prefixSumArr[i] = nums[i - 1] + prefixSumArr[i - 1]
        }
        var retValue = 0
        for (i in nums.indices) {
            for (j in i until nums.size) {
                if (prefixSumArr[j + 1] - prefixSumArr[i + 1] == k) {
                    retValue++
                }
            }
        }
        return retValue
    }

    fun subarraySumO(nums: IntArray, k: Int): Int {
        var retValue = 0
        var prefixSumFreq = mutableMapOf<Int, Int>()
        prefixSumFreq[0] = 1
        var currSum = 0
        for (num in nums) {
            currSum += num
            val complement = currSum - k
            if (prefixSumFreq.contains(complement)) {
                retValue += prefixSumFreq[complement]!!
            }
            prefixSumFreq[currSum] = prefixSumFreq.getOrDefault(currSum, 0) + 1
        }
        return retValue
    }

    fun fourSumCount(nums1: IntArray, nums2: IntArray, nums3: IntArray, nums4: IntArray): Int {
        val sumMap = mutableMapOf<Int, Int>()
        for (a in nums1) {
            for (b in nums2) {
                val sum = a + b
                sumMap[sum] = sumMap.getOrDefault(sum, 0) + 1
            }
        }
        1
        var count = 0
        for (a in nums3) {
            for (b in nums4) {
                val target = -(a + b)
                count += sumMap.getOrDefault(target, 0) + 1
            }
        }
        return count
    }
}