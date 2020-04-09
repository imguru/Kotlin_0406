package com.lge.secondapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lge.secondapp.model.User
import com.lge.secondapp.net.githubApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_search.*


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

// Rx 장점: 비동기의 흐름을 제어를 쉽게할 수 있는 많은 연산이 제공됩니다.

class SearchActivity3 : AppCompatActivity() {
    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        searchButton.setOnClickListener {

            githubApi.rxGetGithubUser("aaa").subscribe {
                githubApi.rxGetGithubUser("bbb").subscribe {

                }
            }
            
            // 2개를 연속으로 호출
            compositeDisposable += githubApi.rxGetGithubUser("imguru")
                .flatMap { user ->
                    githubApi.rxSearchRepo(user.login)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy { response ->
                    val items = response.items
                }

            // 2개의 연산을 합쳐서 한번에 처리하고 싶다.
            /*
            Observables.zip(
                githubApi.rxGetGithubUser("imguru"),
                githubApi.rxGetGithubUser("Jake"),
                githubApi.rxGetGithubUser("Jake")
            ).flatMap { (first, second, third) ->
                githubApi.rxSearchRepo("${first.login}${second.login}")
            }.map {
                it.items
            }.subscribeBy { items ->
                // ....
            }
            */

            data class R(val a: User, val b: User, val c: User, val d: User)

            Observables.zip(
                githubApi.rxGetGithubUser("imguru"),
                githubApi.rxGetGithubUser("Jake"),
                githubApi.rxGetGithubUser("Jake"),
                githubApi.rxGetGithubUser("Jake"),
                { a1, a2, a3, a4 ->
                    R(a1, a2, a3, a4)
                }
            ).flatMap { (first, second, third, fourth) ->
                githubApi.rxSearchRepo("${first.login}${second.login}")
            }.map {
                it.items
            }.subscribeBy { items ->
                // ....
            }


        }

        /*
        searchButton.setOnClickListener {
            // RxKotlin
            compositeDisposable += githubApi.rxGetGithubUser("imguru")
                .map { user -> String
                    user.login
                }
                .filter {
                    it.length > 5
                }
                .observeOn(AndroidSchedulers.mainThread())  // Observer의 동작 스레드
                .subscribeBy(
                    onNext = { user ->
                        Toast.makeText(
                            this,
                            "user - ${user}",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    onError = {

                    },
                    onComplete = {

                    }
                )
        }

        */

        /*
        // RxJava
        githubApi.rxGetGithubUser("imguru")
            .subscribe({ user ->
                // onNext

                Toast.makeText(
                    this,
                    "user - ${user.name}",
                    Toast.LENGTH_SHORT
                ).show()

            }, {
                // onError: 오류가 발생하였을 때

            }, {
                // onComplete: 이벤트 스트림의 끝에 도달했을 때

            }).addTo(compositeDisposable)
         */
    }

    override fun onDestroy() {
        // dispose()
        compositeDisposable.dispose()


        super.onDestroy()
    }

}


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