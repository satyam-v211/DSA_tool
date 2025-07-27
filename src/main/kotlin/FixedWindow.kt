import kotlin.math.max

class FixedWindow {
    fun findMaxAverage(nums: IntArray, k: Int): Double {
        var windowS = 0
        var windowE = k - 1
        var currSum = 0.0
        var currAvg = 0.0
        for (i in 0..windowE) {
            currSum += nums[i]
            currAvg = currSum / (i + 1)
        }
        var retVal = currAvg
        while (windowE < nums.size-1) {
              currSum-=nums[windowS]
              windowS++
              windowE++
              currSum+=nums[windowE]
              currAvg = currSum/k
              retVal = max(currAvg,retVal)
        }
        return retVal
    }
}