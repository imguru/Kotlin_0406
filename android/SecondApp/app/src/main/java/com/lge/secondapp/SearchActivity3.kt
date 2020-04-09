package com.lge.secondapp

import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lge.secondapp.model.Repo
import com.lge.secondapp.model.RepoSearchResponse
import com.lge.secondapp.net.githubApi
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.item_search_repo.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// Rx - 비동기를 다루는 기술(Reactive Extension) vs Kotlin Coroutine(1.3)
//  => 비동기 연산을 일반화한다.
//   : Collection(List) -> map, filter, zip ... -> Result (pull model)

// 동기 호출
//  => 함수의 결과를 바로 알 수 있다.
// ra = a()
// rb = b()
// c(ra, rb)

// 비동기 호출
//  => 결과의 완료 시점을 알 수 없다. 결과의 완료 시점에 수행되어야 하는 콜백을 등록한다.
/*
a() {
  b() {
    c();
  }
}
*/

// Collection
//      List

// Single Linked List
data class Node(var n: Int, var next: Node?)

// Iterator - 컬렉션의 내부 구조에 상관없이 요소를 열거하는 객체
class SListIterator(var current: Node? = null) : Iterator<Int> {
    override fun hasNext(): Boolean = current != null

    override fun next(): Int {
        val n = current!!.n
        current = current?.next

        return n
    }
}

// 모든 Collection은 자신을 열거할 수 있는 Iterator를 제공하는 인터페이스를 구현해야 한다.
// => Iterable
class SList : Iterable<Int> {
    var head: Node? = null

    fun pushFront(n: Int) {
        head = Node(n, head)
    }

    fun front(): Int? = head?.n

    override fun iterator(): Iterator<Int> = SListIterator(head)
}


//    SList             SListIterator
//   Iterable           Iterator         - map, filter ...   pull
//                        next()


//    Rx                                                     push
//  Observable          Observer
//                        onNext()


// Rx - 5가지 요소
// 1. Observable
//     : 이벤트를 만들어내는 주체로 이벤트 스트림을 통해 만든 이벤트를 내보냅니다.

// 2. Observer
//    : Observable에서 만들어진 이벤트에 반응하는 객체, 이벤트를 받았을 때 수행할 작업을 정의합니다.
//   "Observer가 Observable을 구독(subscribe)한다" 라고 합니다.

// 3. Operator
//   : 연산자는 이벤트 스트림을 통해 전달되는 이벤트를 변환합니다.

// 4. Scheduler
//   : 작업을 수행할 스레드를 지정할 수 있습니다.
//    main Thread / IO / Worker / 새로운 스레

// 5. Disposable
//  : Observer가 Observable를 구독할 때 생성되는 객체로, Observable에서 만드는 이벤트 스트림과
//    이에 필요한 리소스를 관리합니다.



class SearchActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        /*
        val slist = SList()
        slist.pushFront(10)
        slist.pushFront(20)
        slist.pushFront(30)

        // val slist = listOf(10, 20, 30)

        for (e in slist)
            Log.e("XXX", "value - $e")

        */
        /*
        val iter = slist.iterator()
        while (iter.hasNext()) {
            Log.e("XXX", "value - ${iter.next()}")
        }
        */


        // Log.e("XXX", "front - ${slist.front()}")
    }
}