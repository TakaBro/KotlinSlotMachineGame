package com.example.taka.kotlinslotmachine

import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.taka.kotlinslotmachine.ImageViewScrolling.IEventEnd
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), IEventEnd {
    override fun eventEnd(result: Int, count: Int) {
        if(count_down < 2)
            count_down++
        else
        {
            down.visibility = View.GONE
            up.visibility = View.VISIBLE

            count_down = 0

            if(image1.value == image2.value && image2.value == image3.value)
            {
                Toast.makeText(this, "You win BIG prize", Toast.LENGTH_SHORT).show()
                Common.SCORE += 300
                txt_score.text = Common.SCORE.toString()
            }
            else    if (image1.value == image2.value || image2.value == image3.value || image1.value == image3.value)
            {
                Toast.makeText(this, "You win small prize", Toast.LENGTH_SHORT).show()
                Common.SCORE += 100
                txt_score.text = Common.SCORE.toString()
            }else
                Toast.makeText(this, "You lose", Toast.LENGTH_SHORT).show()

        }
    }

    internal var count_down = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE

        image1.setEventEnd(this@MainActivity)
        image2.setEventEnd(this@MainActivity)
        image3.setEventEnd(this@MainActivity)

        up.setOnClickListener {
            if(Common.SCORE >= 50)
            {
                up.visibility = View.GONE
                down.visibility = View.VISIBLE

                image1.setValueRandom(Random().nextInt(6),//We have 6 images
                        Random().nextInt(15-5+1)+5)//Get random rotate from 5-15
                image2.setValueRandom(Random().nextInt(6),//We have 6 images
                        Random().nextInt(15-5+1)+5)//Get random rotate from 5-15
                image3.setValueRandom(Random().nextInt(6),//We have 6 images
                        Random().nextInt(15-5+1)+5)//Get random rotate from 5-15

                Common.SCORE -= 50
                txt_score.text = Common.SCORE.toString()
            }
            else
            {
                Toast.makeText(this, "Not have enough money", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
