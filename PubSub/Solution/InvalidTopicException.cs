using System;

namespace PubSub.Solution
{
    [Serializable]
    public class InvalidTopicException : Exception
    {
        public InvalidTopicException(string topic) 
            : base($"Topic '{topic}' is invalid.")
        {
        }
    }
}
/// implementirati sledeće:
/// - da nije prazan
/// - pocinje sa /
/// - / odvaja nivoe
/// - bar 1 level
/// - # može biti samo na kraju