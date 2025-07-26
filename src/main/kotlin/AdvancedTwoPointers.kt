import kotlin.math.max
import kotlin.math.min

class AdvancedTwoPointers {
    fun maxArea(height: IntArray): Int {
        var l = 0
        var r = height.size - 1
        var retValue = 0
        while (l < r) {
            val breadth = r - l
            val currArea = min(height[l], height[r]) * breadth
            retValue = max(currArea, retValue)
            if (height[l] < height[r]) {
                l++
            } else {
                r--
            }
        }
        return retValue
    }

    fun trap(height: IntArray): Int {
        val lMax = IntArray(height.size)
        val rMax = IntArray(height.size)
        lMax[0] = height[0]
        rMax[height.size - 1] = height[height.size - 1]
        for (i in 1..height.size - 1) {
            lMax[i] = max(height[i], lMax[i - 1])
        }
        for (i in height.size - 2 downTo 0) {
            rMax[i] = max(height[i], rMax[i + 1])
        }
        var retValue = 0
        for (i in height.indices) {
            val trappedWater = min(lMax[i], rMax[i]) - height[i]
            retValue += trappedWater
        }
        return retValue
    }

    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        nums.sort()
        val retVal = mutableListOf<List<Int>>()
        for (i in nums.indices) {
            if (i > 0 && nums[i] == nums[i - 1]) continue
            for (j in i+1 until nums.size - 2) {
                if (j > i+1 && nums[j] == nums[j - 1]) continue
                var k = j + 1
                var l = nums.size - 1
                while (k < l) {
                    val sum = 0L + nums[i] + nums[j] + nums[k] + nums[l]
                    if (sum == target.toLong()) {
                        retVal.add(listOf(nums[i], nums[j], nums[k], nums[l]))
                        k++
                        while (k < l && nums[k] == nums[k - 1]) {
                            k++
                        }
                    } else if (sum > target) {
                        l--
                    } else {
                        k++
                    }
                }
            }
        }
        return retVal
    }
}