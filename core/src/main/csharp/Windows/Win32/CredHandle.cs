using System;
using static System.Runtime.CompilerServices.Unsafe;
using static Windows.Win32.CorePInvoke;

namespace Windows.Win32.Security.Credentials
{
    public unsafe sealed class CredHandle : IDisposable
    {
        private bool disposedValue;
        private CREDENTIALW* ptr;

        ~CredHandle()
        {
            Dispose(disposing: false);
        }

        public ref CREDENTIALW Value => ref AsRef<CREDENTIALW>(ptr);

        public void Dispose()
        {
            Dispose(disposing: true);
            GC.SuppressFinalize(this);
        }

        public ref CREDENTIALW* Put() => ref ptr;

        private void Dispose(bool disposing)
        {
            if (!disposedValue)
            {
                if (ptr != default)
                {
                    CredFree(ptr);
                    ptr = default;
                }
                disposedValue = true;
            }
        }
    }
}
