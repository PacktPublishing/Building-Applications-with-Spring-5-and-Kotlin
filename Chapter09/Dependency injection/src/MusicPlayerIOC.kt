

abstract class VolumeControlAbstract

class MusicPlayerIOC(
        private val volumeControl: VolumeControlAbstract
)

class VolumeControlImpl : VolumeControlAbstract()

fun tryIOC(){

    // Init. dependency.
    val vc = VolumeControlImpl()

    // Pass dependency.
    val player = MusicPlayerIOC(vc)

}