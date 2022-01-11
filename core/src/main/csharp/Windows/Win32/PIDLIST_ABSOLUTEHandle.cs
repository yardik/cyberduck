using System;
using static System.Runtime.CompilerServices.Unsafe;
using static Windows.Win32.CorePInvoke;

namespace Windows.Win32.UI.Shell.Common
{
    public unsafe class PIDLIST_ABSOLUTEHandle : IDisposable
    {
        private bool disposedValue;

        private ITEMIDLIST* ptr;

        ~PIDLIST_ABSOLUTEHandle()
        {
            Dispose(disposing: false);
        }

        public ref ITEMIDLIST Value => ref AsRef<ITEMIDLIST>(ptr);

        public static implicit operator bool(in PIDLIST_ABSOLUTEHandle @this) => @this.ptr != default;

        public void Dispose()
        {
            Dispose(disposing: true);
            GC.SuppressFinalize(this);
        }

        public ITEMIDLIST* Get() => ptr;

        public ref ITEMIDLIST* Put() => ref ptr;

        protected virtual void Dispose(bool disposing)
        {
            if (!disposedValue)
            {
                CoTaskMemFree(ptr);
                disposedValue = true;
            }
        }
    }
}
