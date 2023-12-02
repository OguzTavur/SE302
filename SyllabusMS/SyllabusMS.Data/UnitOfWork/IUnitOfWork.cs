namespace SyllabusMS.Data.UnitOfWork
{
    public interface IUnitOfWork
    {
        Task<bool> SaveChangesAsync<T>(T entity) where T : class;
    }
}
