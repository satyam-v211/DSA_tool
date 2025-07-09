import java.util.PriorityQueue

class AdvancedArrayTechniques {

    fun productExceptSelf(nums: IntArray): IntArray {
        val prefArray = IntArray(nums.size)
        prefArray[0] = 1
        val suffArray = IntArray(nums.size)
        suffArray[nums.size-1]=1
        for (i in 1..nums.size-1) {
            prefArray[i] = prefArray[i - 1] * nums[i-1]
        }
        for(i in nums.size-2 downTo 0){
            suffArray[i] = suffArray[i+1] * nums[i+1]
        }
        val retArray = IntArray(nums.size)
        for(i in retArray.indices){
            retArray[i] = prefArray[i] * suffArray[i]
        }
        return retArray
    }

    fun topKFrequent(nums: IntArray, k: Int): IntArray {
      val freqMap = mutableMapOf<Int,Int>()
      for(num in nums){
          freqMap[num] = freqMap.getOrDefault(num,0)+1
      }
      val pq = PriorityQueue<Map.Entry<Int,Int>>(compareByDescending { it.value })
      for(entry in freqMap){
          pq.add(entry)
      }
      val retArray = IntArray(k)
      for(i in retArray.indices){
          retArray[i] = pq.poll().key
      }
        return retArray
    }

}