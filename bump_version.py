#!/user/bin/env python3
import sys

if len(sys.argv) != 3:
    print("Usage: bump_version.py <current_version> <branch_name>", file=sys.stderr)
    sys.exit(1)

# Usage: python bump_version.py <current_version> <branch_name>
version = sys.argv[1]
branch = sys.argv[2]

try:
    major, minor, patch = map(int, version.split('.'))
except ValueError:
    print("Invalid version format. Use MAJOR.MINOR.PATCH", file=sys.stderr)
    sys.exit(1)

if branch.startswith('major/'):
    major += 1
    minor = 0
    patch = 0
elif branch.startswith('minor/'):
    minor += 1
    patch = 0
elif branch.startswith('patch/') or branch.startswith('hotfix/'):
    patch += 1

print(f"{major}.{minor}.{patch}")
