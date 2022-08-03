package com.app.humanresource.Activities.ChatDetailActivity

import android.app.Activity
import android.content.ContentValues
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.humanresource.Activities.ChatDetailActivity.Presenter.ChatDetailPresenter
import com.app.humanresource.Activities.ChatDetailActivity.View.ChatDetailView
import com.app.humanresource.Adapter.ChatAdapterRecyclerview
import com.app.humanresource.Models.GetOldChatsModel.GetOldChatsItem
import com.app.humanresource.R
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.ChatApplication.ChatApplication
import com.app.humanresource.Utils.ChatApplication.Messages
import com.app.humanresource.Utils.Utils
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Socket
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class ChatDetailActivity : AppCompatActivity(), View.OnClickListener, ChatDetailView {
    var activity: Activity? = null
    var img_back: ImageView? = null
    var et_msg: EditText? = null
    var img_messagesend: ImageView? = null
    var chatAdapterRecyclerview: ChatAdapterRecyclerview? = null
    var chatrecyclerview: RecyclerView? = null
    var messageList: ArrayList<Messages> = ArrayList()
    var chatApplication: ChatApplication? = null
    var msgTo :String?=null
    var username: String? = null
    var chatDetailPresenter: ChatDetailPresenter? = null
    var tv_username: TextView? = null



    //    var socket:Socket?=null
    var mSocket: com.github.nkzawa.socketio.client.Socket? = null
    private var toUser: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        messageList = ArrayList()
        val app = ChatApplication()


        //mSocket = app.getmSocket();
//        helper = PreferenceHelper.getInstance(getActivity());
        // roomId = helper.getroomid();

        //mSocket = app.getmSocket();
//        helper = PreferenceHelper.getInstance(getActivity());
        // roomId = helper.getroomid();
        mSocket = app.getmSocket()
//        mSocket.connect();

        //        mSocket.connect();
        mSocket!!.on(Socket.EVENT_CONNECT, onConnect)
        mSocket!!.on(Socket.EVENT_CONNECT_ERROR, onConnectionError)
        //  mSocket.on("set-user-data",)
        //  mSocket.on("set-user-data",)
        mSocket!!.on("set-room", onGettingRoomId)
        mSocket!!.on("chat-msg", onNewMessage)
        //mSocket.on("typing", onTyping);
        //mSocket.on("typing", onTyping);
        mSocket!!.connect()
        Log.d(ContentValues.TAG, "socket" + mSocket!!.connected())
        mSocket = app.getmSocket()

        setContentView(R.layout.activity_chat_detail)
        activity = this
        init()
        listeners()
        chatDetailPresenter = ChatDetailPresenter(activity as ChatDetailActivity, this, messageList)
        msgTo = intent.getStringExtra("username").toString()
        tv_username?.text = msgTo

        var username = CSPreferences.readString(activity as ChatDetailActivity, Utils.USERNAME)
        chatAdapterRecyclerview = ChatAdapterRecyclerview(messageList)
        chatrecyclerview!!.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        chatrecyclerview?.adapter = chatAdapterRecyclerview

    }

    private fun init() {
        img_back = findViewById(R.id.img_back)
        et_msg = findViewById(R.id.et_msg)
        img_messagesend = findViewById(R.id.img_messagesend)
        chatrecyclerview = findViewById(R.id.chatrecyclerview)
        tv_username = findViewById(R.id.tv_username)
    }


    private fun listeners() {
        img_back?.setOnClickListener(this)
        img_messagesend?.setOnClickListener(this)
    }


    override fun onClick(p0: View?) {
        if (p0 == img_back) {
            finish()
        } else if (p0 == img_messagesend) {

            attemptSend()


        }
    }


    private fun attemptSend() {
//        if (!mSocket.connected()) return
        val message: String = et_msg?.getText().toString()
        if (TextUtils.isEmpty(message)) {
            et_msg?.requestFocus()
            return
        }
        et_msg?.setText("")
        val calendar = Calendar.getInstance()
        //        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//        val mdformat = SimpleDateFormat("dd/MM/yyyy hh.mm aa")
        val mdformat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val strDate = mdformat.format(calendar.time)
//        Log.d(ContentValues.TAG, "attemptSenddate$strDate")
        Log.d("checkjaggasirdate", "attemptSenddate$strDate")

        // String date = "2021-02-16T15:24:44.000Z";
        val jsonObject = JSONObject()
        try {
            jsonObject.put("msg", message)
            jsonObject.put("msgTo", msgTo)
            jsonObject.put("date", strDate)
//            jsonObject.put("date", "1657258541457")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        Log.d(ContentValues.TAG, "$jsonObject   emit this")
//        Log.d(ContentValues.TAG, "socket" + mSocket.connected())
        addMessage(strDate, message, Messages.TYPE_MESSAGE)
        mSocket?.emit("chat-msg", jsonObject)
    }


    private fun addMessage(time: String, message: String, type: Int) {
        messageList.add(Messages.Builder(type).time(time).message(message).build())
        chatAdapterRecyclerview!!.notifyItemInserted(messageList!!.size - 1)
//        scrollToBottom();
    }


    var onConnect = Emitter.Listener {
        Log.d(ContentValues.TAG, "Socket Connected!" + mSocket!!.connected())
        activity!!.runOnUiThread {
            Toast.makeText(activity, "connected", Toast.LENGTH_SHORT).show()
            //                    if (!isconnected) {
            //                        Snackbar s = Snackbar.make(binding.getRoot(), "Loading..", Snackbar.LENGTH_LONG);
            //                        s.getView().setBackgroundColor(binding.getRoot().getResources().getColor(R.color.orange));
            //                        isconnected = true;
            //                        s.show();
            //                    }
        }
    }


    private val onConnectionError = Emitter.Listener {
        activity!!.runOnUiThread {
            Toast.makeText(activity, "Connection error", Toast.LENGTH_SHORT).show()
        }
    }


    private val onGettingRoomId = Emitter.Listener {
        activity!!.runOnUiThread {
            var roomId = it[0].toString();
            Log.d(" checkroomid", roomId)
            chatDetailPresenter?.getOldchat(roomId)
            //                    if (!roomId.isEmpty()) {
            //                        //  getOldChats(roomId);
            //                    }
        }
    }


    private val onNewMessage =
        Emitter.Listener { args ->
            if (activity != null) {
                activity!!.runOnUiThread(Runnable {
                    chatrecyclerview!!.post {
                        chatrecyclerview!!.scrollToPosition(chatAdapterRecyclerview!!.itemCount - 1)
                        // Here adapter.getItemCount()== child count
                    }
                    val jsonObject = args[0] as JSONObject

//                    if (jsonObject.has("msgFrom") && jsonObject.optString("msgFrom")
//                            .equals(toUser, ignoreCase = true)
                    var msgFrom = jsonObject.getString("msgFrom")
                    if (msgFrom != username) {
                        val time: String
                        val message: String
                        try {
                            time = jsonObject.getString("date")
                            message = jsonObject.getString("msg")
                            Toast.makeText(
                                activity,
                                jsonObject.getString("msgFrom"),
                                Toast.LENGTH_SHORT
                            ).show()

                            Log.d(ContentValues.TAG, "$message   received msg")
                            Log.d("Checkrecivedmsg", "$message   received msg")
                        } catch (e: JSONException) {
                            return@Runnable
                        }
                        addMessage(time, message, Messages.TYPE_MESSAGE_REMOTE)
                    } else {
                        Log.d("error", "Error")
                    }
//
                })
            }
        }

    //    private void scrollToBottom() {
    ////        chatrecyclerview.scrollToPosition(chatAdapterRecyclerview.getItemCount() - 1);
    //    }
    /*
    private Emitter.Listener onTyping = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            if (activity!= null)
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String typingMess = args[0].toString();
                        animFadeIn = AnimationUtils.loadAnimation(activity, R.anim.fade_in);
                        animFadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);

                        binding.txtTyping.setAnimation(animFadeIn);
                        binding.txtTyping.setVisibility(View.VISIBLE);
                        if (!mTyping)
                            mTyping = true;

                        if (mTyping) {
                            mTypingHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    binding.txtTyping.setVisibility(View.GONE);
                                    binding.txtTyping.setAnimation(animFadeOut);
                                    mTyping = false;
                                }
                            }, 1500);
                        }
                    }
                });
        }
    };
*/
    //    private void getOldChats(String roomId) {
    //
    //        new RetrofitService(getActivity(), ServiceUrls.OLDMESSAGES + "?room_id=" + roomId + "&pageNo=" + currentPage
    //                +"&pageSize=1000000",
    //                1, 1, new JsonObject(), true, this).callService(false);
    //    }
    override fun onStart() {
        super.onStart()
        if (!mSocket!!.connected()) {
            mSocket!!.connect()
            Log.d(ContentValues.TAG, "onStart: " + mSocket!!.connect())
        }
        mSocket!!.emit("set-user-data", username)
    }

    override fun onResume() {
        super.onResume()
        mSocket!!.connect()
        username = CSPreferences.readString(activity as ChatDetailActivity, Utils.USERNAME)
//        username = CSPreferences.readString(activity, Utils.USERNAME)
//        userId = CSPreferences.readString(activity, Utils.USERID)

//        toUser =  PreferenceHelper.getInstance(activity).getfriendname();
//        val currentRoom: String = username + "-" + toUser
////        val currentRoom: String = username + "-" + "employee"
//        val reverseRoom = "$toUser-$username"
//        val reverseRoom = "employee" + "-" + username
//        Log.d("chchhchc",currentRoom)
//        Log.d(ContentValues.TAG, "current room " + currentRoom + "reverse room " + reverseRoom)
        val jsonObject = JSONObject()
        try {
            jsonObject.put("name1", username + "-" + msgTo)
            jsonObject.put("name2", msgTo + "-" + username)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        mSocket!!.emit("set-room", jsonObject)
        mSocket!!.emit("set-user-data", username)
//        mSocket!!.on("set-room",onGettingRoomId)

    }

    override fun showMessage(activity: Activity?, msg: String?) {

    }

    override fun showDialog(activity: Activity?) {
    }

    override fun hideDialog() {
    }

    override fun setData(activity: ChatDetailActivity, items: List<GetOldChatsItem>) {
        for (i in 0..items.size-1) {
            addMessage(
                items.get(i).createdOn,
                items.get(i).msg,
                Messages.TYPE_MESSAGE
            )

            Toast.makeText(activity, items.get(0).createdOn, Toast.LENGTH_SHORT).show()
//                    }
        }
    }


    }