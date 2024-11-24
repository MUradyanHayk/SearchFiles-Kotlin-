import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

object SearchFileUtils {

    fun searchFiles(rootFile: File, fileList: MutableList<File>) {
        if (!rootFile.isDirectory) {
            return
        }

        val files = rootFile.listFiles() ?: return
        for (file in files) {
            if (file.isDirectory) {
                searchFiles(file, fileList)
            } else if (file.name.endsWith(".jpg")) {
                fileList.add(file)
                copyToGivenDirectory(file, "src/copiedFiles")
                println(file.name)
            }
        }
    }

    fun deleteFiles(rootFile: File) {
        if (!rootFile.isDirectory) {
            return
        }

        val files = rootFile.listFiles() ?: return
        for (file in files) {
            if (file.isDirectory) {
                deleteFiles(file)
            } else if (file.name.endsWith(".jpg")) {
                println(file.name)
                deleteFile(file, "src/copiedFiles")
            }
        }
    }

    fun copyToGivenDirectory(file: File, currentDirectory: String) {
        val copiedBytes: ByteArray
        val inputStream = FileInputStream(file).use { input ->
            val bufferedInputStream = BufferedInputStream(input).use {
                copiedBytes = it.readBytes()
            }
        }

        val copiedFile = File(currentDirectory, "${file.name}_copy.jpg")
        val outputStream = FileOutputStream(copiedFile).use { output ->
            val bufferedOutputStream = BufferedOutputStream(output).use {
                it.write(copiedBytes)
            }
        }
    }

    private fun deleteFile(file: File, directory: String) {
        if (file.exists()) {
            file.delete()
        }
    }
}