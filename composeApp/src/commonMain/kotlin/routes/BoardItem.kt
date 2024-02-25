package routes

import koreatechboard.composeapp.generated.resources.*
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

@OptIn(ExperimentalResourceApi::class)
sealed class BoardItem(
    val stringResource: StringResource,
    val painterResource: String,
    val board: String
) {
    data object Notice : BoardItem(Res.string.board_notice, "drawable/ic_bottom_navigation_notice", "notice")
    data object Free :
        BoardItem(Res.string.board_free_board, "drawable/ic_bottom_navigation_freeboard", "free")
    data object Job : BoardItem(Res.string.board_job_board, "drawable/ic_bottom_navigation_jobboard", "job")
    data object Lecture : BoardItem(Res.string.board_lecture_board, "drawable/ic_bottom_navigation_lecture", "lecture")
    data object Scholar : BoardItem(Res.string.board_scholar_board, "drawable/ic_bottom_navigation_scholar", "scholar")
    data object Bachelor : BoardItem(Res.string.board_bachelor_board, "drawable/ic_bottom_navigation_bachelor", "bachelor")
    data object PDS : BoardItem(Res.string.board_pds_board, "drawable/ic_bottom_navigation_pds", "pds")
}

val cseBoards = listOf(
    BoardItem.Notice,
    BoardItem.Free,
    BoardItem.Job,
    BoardItem.PDS
)

val archBoards = listOf(
    BoardItem.Notice,
    BoardItem.Free
)

val ideBoards = listOf(
    BoardItem.Notice,
    BoardItem.Free
)

val mechanicalBoards = listOf(
    BoardItem.Notice
)

val mechatronicsBoards = listOf(
    BoardItem.Notice,
    BoardItem.Lecture,
    BoardItem.Bachelor,
    BoardItem.Job,
    BoardItem.Free
)

val schoolBoards = listOf(
    BoardItem.Notice,
    BoardItem.Scholar,
    BoardItem.Bachelor
)

val dormBoards = listOf(
    BoardItem.Notice
)

val iteBoards = listOf(
    BoardItem.Notice
)

val simBoards = listOf(
    BoardItem.Notice
)

val emcBoards = listOf(
    BoardItem.Notice
)
