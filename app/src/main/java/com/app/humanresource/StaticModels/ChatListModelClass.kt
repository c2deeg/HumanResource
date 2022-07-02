package com.app.humanresource.StaticModels

class ChatListModelClass {
    var imgae:Int?= 0
    var name:String?=null
    var time:String?=null
    var message:String?=null

    constructor(imgae: Int?, name: String?, time: String?, message: String?) {
        this.imgae = imgae
        this.name = name
        this.time = time
        this.message = message
    }
}