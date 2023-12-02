namespace SyllabusMS.Data.UnitOfWork
{
    public class EFUnitOfWork : IUnitOfWork
    {
        public EFUnitOfWork(MyDbContext dbContext)
        {
            _context = dbContext;
        }
        private readonly MyDbContext _context;
        public async Task<bool> SaveChangesAsync<T>(T entity) where T : class
        {
            bool result;
            var transaction = _context.Database.BeginTransaction();
            try
            {
                _ = await _context.SaveChangesAsync().ConfigureAwait(false);
                transaction.Commit();
                result = true;
            }
            catch
            {
                transaction.Rollback();
                result = false;
            }
            return result;
        }
    }
}
