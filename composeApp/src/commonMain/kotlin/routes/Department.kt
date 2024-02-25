package routes

import koreatechboard.composeapp.generated.resources.*
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

@OptIn(ExperimentalResourceApi::class)
sealed class Department(val name: String, val stringResource: StringResource, val boards: List<BoardItem>) {
    object School : Department("school", Res.string.department_school, schoolBoards)
    object Dorm : Department("dorm", Res.string.department_dorm, dormBoards)
    object Cse : Department("cse", Res.string.department_cse, cseBoards)
    object Mechanical : Department("mechanical", Res.string.department_mechanical, mechanicalBoards)
    object Mechatronics : Department("mechatronics", Res.string.department_mechatronics, mechatronicsBoards)
    object Ite : Department("ite", Res.string.department_ite, iteBoards)
    object Ide : Department("ide", Res.string.department_ide, ideBoards)
    object Arch : Department("arch", Res.string.department_arch, archBoards)
    object Emc : Department("emc", Res.string.department_emc, emcBoards)
    object Sim : Department("sim", Res.string.department_sim, simBoards)
}

val deptList = listOf(
    Department.School,
    Department.Dorm,
    Department.Cse,
    Department.Mechanical,
    Department.Mechatronics,
    Department.Ite,
    Department.Ide,
    Department.Arch,
    Department.Emc,
    Department.Sim
)
