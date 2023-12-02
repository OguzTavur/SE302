using Microsoft.Data.Sqlite;
using Microsoft.EntityFrameworkCore;

namespace SyllabusMS.Data
{
    public abstract class MyDbContext : DbContext
    {
        #region Constructor

        public MyDbContext()
        {
            if (!Database.CanConnect()) _ = Database.EnsureCreatedAsync();
        }

        #endregion Constructor

        #region OnConfiguring

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
                var connectionStringBuilder = new SqliteConnectionStringBuilder { DataSource = "" }; //TODO: manage the sqlite db
                var connectionString = connectionStringBuilder.ToString();
                var connection = new SqliteConnection(connectionString);

                optionsBuilder.UseSqlite(connection);
            }
        }

        #endregion OnConfiguring

        #region OnModelCreating

        protected override void OnModelCreating(ModelBuilder optionsBuilder)
        {
            base.OnModelCreating(optionsBuilder);
        }

        #endregion OnModelCreating
    }
}
