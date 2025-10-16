import java.util.Collections
import java.util.PriorityQueue
import kotlin.math.max
import kotlin.math.min

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

        fun characterReplacement(s: String, k: Int): Int {
            var windowStart = 0
            var maxLength = 0
            var maxRepeatCharCount = 0
            val charFrequency = mutableMapOf<Char, Int>()

            for (windowEnd in s.indices) {
                val rightChar = s[windowEnd]
                charFrequency[rightChar] = charFrequency.getOrDefault(rightChar, 0) + 1
                maxRepeatCharCount = maxOf(maxRepeatCharCount, charFrequency[rightChar]!!)

                // The number of characters to replace is the window size minus the count of the most frequent character.
                // If this number exceeds k, we must shrink the window.
                if ((windowEnd - windowStart + 1) - maxRepeatCharCount > k) {
                    val leftChar = s[windowStart]
                    charFrequency[leftChar] = charFrequency[leftChar]!! - 1
                    windowStart++
                }

                // The result is the size of the largest valid window we have found.
                maxLength = maxOf(maxLength, windowEnd - windowStart + 1)
            }

            return maxLength
        }

        fun minWindow(s: String, t: String): String {
            if (s.length < t.length) return ""
            val freqMapT = mutableMapOf<Char, Int>()
            for (c in t) {
                freqMapT[c] = freqMapT.getOrDefault(c, 0) + 1
            }
            var windowS = 0
            var windowE = 0
            var validCount = 0
            var minLength = Integer.MAX_VALUE
            var start = 0
            val freqMapWindow = mutableMapOf<Char, Int>()
            while (windowE < s.length) {
                val c = s[windowE]
                freqMapWindow[c] = freqMapWindow.getOrDefault(c, 0) + 1
                if (freqMapT.contains(c) && freqMapT[c] == freqMapWindow[c]) {
                    validCount++
                }
                while (validCount == freqMapT.size) {
                    if (windowE - windowS + 1 < minLength) {
                        minLength = windowE - windowS + 1
                        start = windowS
                    }
                    val leftChar = s[windowS]
                    freqMapWindow[leftChar] = freqMapWindow[leftChar]!! - 1
                    if (freqMapT.contains(leftChar) && freqMapWindow[leftChar]!! < freqMapT[leftChar]!!) {
                        validCount--
                    }
                    windowS++
                }
                windowE++
            }
            return if (minLength == Integer.MAX_VALUE) "" else s.substring(start, start + minLength)
        }
    }

    class AdvancedSlidingWindow {
        fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
            val retArray = IntArray(nums.size-k+1)
            var windowS = 0
            var windowE = 0
            val pq = PriorityQueue(Collections.reverseOrder<Int>())
            var currI = 0
            while (windowE < nums.size) {
                val currSize = windowE - windowS
                if (currSize == k) {
                    retArray[currI++] = pq.peek()
                    pq.remove(nums[windowS])
                    windowS++
                }
                pq.add(nums[windowE++])
            }
            if (pq.isNotEmpty()) {
                retArray[currI] = pq.peek()
            }
            return retArray
        }
    }
}
