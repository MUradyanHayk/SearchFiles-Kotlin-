import java.io.File

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val fileList = mutableListOf<File>()
    SearchFileUtils.searchFiles(File("F:\\wollpaper\\"), fileList)
}