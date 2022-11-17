class Card(value : Int, resourceId : Int) {
    private var value : Int
    private var resourceId : Int

    init {
        this.value = value
        this.resourceId = resourceId
    }

    fun getValue() : Int {
        return value
    }

    fun getResourceId() : Int {
        return resourceId
    }
}