namespace SyllabusMS.Data.UnitOfWork
{
    public interface ICrud<T> where T : class, new()
    {
        Task<IEnumerable<T>> GetAllAsync();
        Task<bool> InsertAsync(T entity);
        Task<bool> UpdateAsync(T entity);
        Task<bool> DeleteAsync(T entity);
        Task<List<T>> PredicateAsync(T entity);
        Task<T> FirstOrDefaultAsync(T entity);
    }
}
