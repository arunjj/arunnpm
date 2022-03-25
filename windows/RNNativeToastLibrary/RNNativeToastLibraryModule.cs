using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Native.Toast.Library.RNNativeToastLibrary
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNNativeToastLibraryModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNNativeToastLibraryModule"/>.
        /// </summary>
        internal RNNativeToastLibraryModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNNativeToastLibrary";
            }
        }
    }
}
