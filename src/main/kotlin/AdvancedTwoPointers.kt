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
}