package com.example.reproductormusicakotlin

data class song(
  val title : String,
  val audioResId : Int,
  val imageResId : Int
){
}
class AppConstans {
    companion object {
        const val LOG_MAIN_ACTIVITY = "MainActivityReproductor"
        const val MEDIA_PLAYER_POSITION = "mpPosition"

        val songs= listOf(
            song("Pretty please - Dua Lipa",R.raw.pp_remix, R.drawable.pretty_please),
            song("Summertime sadness - Lana del Rey", R.raw.lr_ss, R.drawable.lr_ss),
            song("In the end - Linkin Park", R.raw.lp_in_the_end_remix, R.drawable.lp_in_the_emd_remix)
        )
    }
}