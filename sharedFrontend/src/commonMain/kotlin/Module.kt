import gateway.TaskGateway
import org.koin.core.context.startKoin
import org.koin.dsl.module
import repository.TaskRepository
import viewmodel.CreateTaskViewModel
import viewmodel.HomeViewModel

fun initKoinModule() = startKoin {
    modules(
        module {
            single {
                TaskRepository(get())
            }
            single {
                TaskGateway()
            }
            factory {
                HomeViewModel(get())
            }
            factory {
                CreateTaskViewModel(get())
            }
        }
    )
}
