using SyllabusMS.Data;
using SyllabusMS.Data.Repositories;

namespace SyllabusMS.BaseBusiness.Base
{
    public class BaseBusiness<T> : MyDbContext where T : class, new()
    {
        protected IRepository<T> Query;

        protected BaseBusiness()
        {
            Query = new EFRepository<T>(this);
        }
    }
}
