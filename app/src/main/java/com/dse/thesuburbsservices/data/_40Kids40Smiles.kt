package com.dse.thesuburbsservices.data

import com.dse.thesuburbsservices.EMPTY_STRING

// _40Kids40Smiles
data class _40Kids40Smiles(
   var heading: String = EMPTY_STRING,
   var content: String = EMPTY_STRING,
   var objectives: String = EMPTY_STRING,
   var images: Array<String>? = null,
   var imageCaptions: Array<String>? = null
)