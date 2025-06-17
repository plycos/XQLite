import sys
import xml.etree.ElementTree as ET

path_to_pom = sys.argv[1]
bump_version = sys.argv[2]

tree = ET.parse(path_to_pom)
ns = {'m': 'http://maven.apache.org/POM/4.0.0'}

root = tree.getroot()

version_el = root.find('m:version', ns)
if version_el is not None:
    version_el.text = bump_version
    ET.register_namespace('', 'http://maven.apache.org/POM/4.0.0')
    tree.write(path_to_pom, encoding='utf-8', xml_declaration=True)
else:
    print('Version tag not found', file=sys.stderr)
    sys.exit(1)
