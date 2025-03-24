fun main()
{
    // содаем объект пекаря
    val baker : Baker  = Baker()
    // создаем билдер для ржаного хлеба
    var builder : BreadBuilder = RyeBreadBuilder()
    // выпекаем
    val ryeBread : Bread  = baker.Bake(builder)
    println(ryeBread.ToString())
    // оздаем билдер для пшеничного хлеба
    builder = WheatBreadBuilder()
    val wheatBread : Bread = baker.Bake(builder)
    println(wheatBread.ToString())
}
// абстрактный класс строителя
abstract class BreadBuilder
{
    lateinit var bread : Bread
    fun CreateBread() {
        bread = Bread()
    }
    abstract fun SetFlour()
    abstract fun SetSalt()
    abstract fun SetAdditives()
}
// пекарь
class Baker
{
    fun Bake(breadBuilder : BreadBuilder): Bread
    {
        breadBuilder.CreateBread()
        breadBuilder.SetFlour()
        breadBuilder.SetSalt()
        breadBuilder.SetAdditives()
        return breadBuilder.bread
    }
}
// строитель для ржаного хлеба
class RyeBreadBuilder : BreadBuilder()
{
    override fun SetFlour() {
        this.bread.flour = Flour("Ржаная мука 1 сорт")
    }

    override fun SetSalt() {
        this.bread.salt = Salt()
    }

    override fun SetAdditives() {
        // не используется
    }
}
// строитель для пшеничного хлеба
class WheatBreadBuilder : BreadBuilder()
{
    override fun SetFlour() {
        bread.flour = Flour("Пшеничная мука высший сорт" )
    }

    override fun SetSalt() {
        bread.salt = Salt()
    }

    override fun SetAdditives() {
        bread.additives = Additives("Улучшитель хлебопекарный" )
    }
}

//мука
class Flour(sortPar : String) {
    // какого сорта мука
    var sort : String = sortPar
}
// соль
class Salt
{}
// пищевые добавки
class Additives(namePar : String) {
    var name : String = namePar
}

class Bread {
    // мука
    var flour : Flour? = null
    // соль
    var salt : Salt? = null
    // пищевые добавки
    var additives : Additives? = null
    fun ToString() : String
    {
        var sb : String = ""
        if (flour != null)
            sb += flour!!.sort + "\n"
        if (salt != null)
            sb += ("Соль \n")
        if (additives != null)
            sb += "Добавки: " + additives!!.name+" \n"
        return sb
    }
}