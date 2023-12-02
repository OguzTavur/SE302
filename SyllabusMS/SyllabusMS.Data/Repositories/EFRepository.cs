using Microsoft.EntityFrameworkCore;
using SyllabusMS.Data.UnitOfWork;
using System.Reflection;

namespace SyllabusMS.Data.Repositories
{
    public class EFRepository<T> : IRepository<T> where T : class
    {
        private readonly MyDbContext _context;
        private readonly IUnitOfWork _uow;

        public EFRepository(MyDbContext context)
        {
            _context = context;
            _uow = new EFUnitOfWork(context);
        }
        public IQueryable<T> GetAll() => _context.Set<T>();

        public async Task<IEnumerable<T>> GetAllAsync() => await _context.Set<T>().ToListAsync();

        public T GetById(int id) => _context.Set<T>().Find(id)!;

        #region Insert
        public async Task<bool> InsertAsync(T entity)
        {
            _context.Set<T>().Add(entity);
            return await _uow.SaveChangesAsync(entity).ConfigureAwait(true);
        }
        #endregion

        #region Update
        public async Task<bool> UpdateAsync(T entity)
        {
            GetId(entity, entity.GetType().GetProperties());

            return await _uow.SaveChangesAsync(entity).ConfigureAwait(true);
        }

        private void GetId(T entity, PropertyInfo[] props)
        {
            for (int i = 0; i < props.Length; i++)
            {
                if (props[i].Name == "Id")
                {
                    _context.Entry(_context.Set<T>().Find(props[i].GetValue(entity))).CurrentValues.SetValues(entity);
                    break;
                }
            }
        }
        #endregion

        #region Delete
        public async Task<bool> DeleteAsync(T entity)
        {
            if (GetTypeProperty(entity))
                return await UpdateAsync(entity).ConfigureAwait(true);
            return false;
        }

        private static bool GetTypeProperty(T entity)
        {
            if (entity.GetType().GetProperty("Deleted") == null)
                return false;

            entity.GetType()
                  .GetProperty("Deleted")!
                  .SetValue(entity, 1);

            return true;
        }

        #endregion
    }
}
