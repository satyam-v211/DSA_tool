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
        while (windowE < nums.size - 1) {
            currSum -= nums[windowS]
            windowS++
            windowE++
            currSum += nums[windowE]
            currAvg = currSum / k
            retVal = max(currAvg, retVal)
        }
        return retVal
    }

    fun checkInclusion(s1: String, s2: String): Boolean {
        if(s2.length < s1.length) return false
        val freqArrS1 = IntArray(26)
        for (s in s1) {
            freqArrS1[s - 'a'] += 1
        }
        var windowS = 0
        var windowE = s1.length - 1
        val sb = StringBuilder()
        for (i in 0..windowE) {
            sb.append(s2[i])
        }
        val freqArrS2 = IntArray(26)
        for (s in sb) {
            freqArrS2[s - 'a'] += 1
        }
        if (freqArrS1.contentEquals(freqArrS2)) {
            return true
        }
        while (windowE < s2.length - 1) {
            freqArrS2[s2[windowS] - 'a'] -= 1
            windowS++
            windowE++
            freqArrS2[s2[windowE] - 'a'] += 1
            if (freqArrS1.contentEquals(freqArrS2)) {
                return true
            }
        }
        return false
    }
}