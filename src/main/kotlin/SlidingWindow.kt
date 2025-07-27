import kotlin.math.max

class SlidingWindow {
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
            if (s2.length < s1.length) return false
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

    class VariableWindow {
        fun lengthOfLongestSubstring(s: String): Int {
            var windowS = 0
            var windowE = 0
            if (s.length == 0) return 0
            val subSet = mutableSetOf(s[0])
            var retVal = 1
            while (windowE < s.length - 1) {
                if (subSet.contains(s[windowE + 1])) {
                    subSet.remove(s[windowS])
                    windowS++
                } else {
                    windowE++
                    subSet.add(s[windowE])
                    retVal = max(retVal, subSet.size)
                }
            }
            return retVal
        }

        fun lengthOfLongestSubstring2(s: String): Int {
            val map = mutableMapOf<Char, Int>()
            var maxLen = 0
            var start = 0
            for (end in s.indices) {
                if (map.containsKey(s[end])) {
                    start = maxOf(start, map[s[end]]!! + 1)
                }
                map[s[end]] = end
                maxLen = maxOf(maxLen, end - start + 1)
            }
            return maxLen
        }
    }
}
