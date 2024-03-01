package data.mapper

import data.model.ArticleResponse
import domain.base.APIResult
import domain.base.ErrorType
import domain.model.Article
import domain.model.Files


fun ArticleResponse.mapToArticle(): APIResult<Article> {
    val mappedFileList = ArrayList<Files>()

    if (this.files.isNotEmpty()) {
        for (files in this.files) {
            mappedFileList.add(
                Files(
                    fileName = files.fileName,
                    fileUrl = files.fileUrl
                )
            )
        }
    }

    return if (this.statusCode == 200) {
        APIResult.Success(
            Article(
                statusCode = this.statusCode,
                title = this.title,
                writer = this.writer,
                content = this.content,
                date = this.date,
                articleUrl = this.articleUrl,
                files = mappedFileList
            )
        )
    } else {
        APIResult.Error(ErrorType(this.statusCode, this.error))
    }
}
